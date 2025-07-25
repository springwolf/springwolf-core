/* SPDX-License-Identifier: Apache-2.0 */
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import {
  AfterViewInit,
  Component,
  ContentChildren,
  ElementRef,
  OnInit,
  QueryList,
  signal,
  ViewChild,
} from "@angular/core";
import { NavigationTargetDirective } from "./navigation.directive";
import { AsyncApiMapperService } from "../../service/asyncapi/asyncapi-mapper.service";
import { CommonModule, Location } from "@angular/common";
import { MatSidenavModule } from "@angular/material/sidenav";
import { MatIconModule } from "@angular/material/icon";

interface NavigationEntryTag {
  type: string;
  value: string;
}
interface NavigationEntry {
  name: string[];
  icon?: string;
  href: string | undefined;
  selected?: boolean;
  expanded?: boolean;
  tags?: NavigationEntryTag[];
  children?: NavigationEntry[];
}

@Component({
  selector: "app-sidenav",
  templateUrl: "./sidenav.component.html",
  styleUrls: ["./sidenav.component.css"],
  imports: [MatSidenavModule, MatIconModule, CommonModule],
})
export class SidenavComponent implements OnInit, AfterViewInit {
  @ViewChild("scrollableElement") scrollableElement!: ElementRef;
  @ContentChildren(NavigationTargetDirective, { descendants: true })
  navigationTargets!: QueryList<NavigationTargetDirective>;

  navigation = signal<NavigationEntry[]>([]);

  constructor(
    private asyncApiService: AsyncApiService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.location.subscribe(this.scrollToUrlLocation);

    this.asyncApiService.getAsyncApi().subscribe((asyncapi) => {
      const newNavigation: NavigationEntry[] = [];

      newNavigation.push({
        name: ["Info"],
        icon: "info",
        href: AsyncApiMapperService.BASE_URL + "info",
      });

      const servers: NavigationEntry[] = Array.from(
        asyncapi.servers.keys()
      ).map((key) => ({
        name: this.splitForWordBreaking(key),
        href:
          AsyncApiMapperService.BASE_URL +
          asyncapi.servers.get(key)!.anchorIdentifier,
        tags: [
          { type: "protocol", value: asyncapi.servers.get(key)!.protocol },
        ],
      }));
      newNavigation.push({
        name: ["Servers"],
        icon: "dns",
        href: AsyncApiMapperService.BASE_URL + "servers",
        children: servers,
      });

      const channels = {
        name: ["Channels & Operations"],
        icon: "swap_vert",
        href: AsyncApiMapperService.BASE_URL + "channels",
        children: [] as NavigationEntry[],
      };
      asyncapi.channels.forEach((value) => {
        let children = value.operations.map((operation) => {
          return {
            name: this.splitForWordBreaking(operation.operation.message.title),
            href: AsyncApiMapperService.BASE_URL + operation.anchorIdentifier,
            tags: [
              {
                type: "operation-" + operation.operation.operationType,
                value: operation.operation.operationType,
              },
            ],
          };
        });
        const tags = children
          .flatMap((child) => child.tags)
          .flatMap((tags) => tags);

        const channel = {
          name: this.splitForWordBreaking(value.name),
          href: AsyncApiMapperService.BASE_URL + value.anchorIdentifier,
          tags: this.filterAndSort(tags, "value"),
          children: children,
        };

        channels.children.push(channel);
      });
      newNavigation.push(channels);

      const schemas = {
        name: ["Schemas"],
        icon: "schema",
        href: AsyncApiMapperService.BASE_URL + "schemas",
        children: [] as NavigationEntry[],
      };
      asyncapi.components.schemas.forEach((value) => {
        schemas.children.push({
          name: this.splitForWordBreaking(value.title),
          href: AsyncApiMapperService.BASE_URL + "" + value.anchorIdentifier,
        });
      });
      newNavigation.push(schemas);

      this.navigation.set(newNavigation);

      this.scrollToUrlLocation();
    });
  }

  private splitForWordBreaking = (text: string) => {
    // Split by set of characters, but keep separators
    return text.split(/(?<=[.,_/\-])/);
  };

  private filterAndSort<A>(arr: A[], key: keyof A): A[] {
    const seen = new Set<string>();
    const uniqueArr = arr.filter((item) => {
      const serializedItem = JSON.stringify(item);
      return seen.has(serializedItem) ? false : seen.add(serializedItem);
    });

    return uniqueArr.sort((a, b) => {
      if (a[key] < b[key]) return -1;
      if (a[key] > b[key]) return 1;
      return 0;
    });
  }

  ngAfterViewInit() {
    this.scrollableElement.nativeElement.addEventListener(
      "scroll",
      this.updateNavigationSelection
    );
  }

  private updateNavigationSelection = () => {
    let currentAnchor = "";
    const scrollPosition = this.scrollableElement.nativeElement.scrollTop;

    document
      .querySelectorAll("[appNavigationTarget]")
      // this.navigationTargets .map((el: NavigationTargetDirective)=> el.el.nativeElement)
      .forEach((element: Element) => {
        const nativeElement = element as HTMLElement;
        const anchorPosition = nativeElement.offsetTop;
        const anchorHeight = nativeElement.offsetHeight;
        if (
          scrollPosition >= anchorPosition &&
          scrollPosition < anchorPosition + anchorHeight
        ) {
          currentAnchor =
            AsyncApiMapperService.BASE_URL +
            "" +
            nativeElement.getAttribute("id");
        }
      });

    this.navigation().forEach((link) => {
      let childSelected = false;
      link.children?.forEach((child) => {
        let subChildSelected = false;
        child.children?.forEach((subChild) => {
          subChild.selected = currentAnchor == subChild.href;
          subChild.expanded = subChild.selected;

          subChildSelected = subChildSelected || subChild.selected;
        });
        child.selected = currentAnchor == child.href || subChildSelected;
        childSelected = childSelected || child.selected;

        child.children?.forEach((subChild) => {
          subChild.expanded = child.selected;
        });
      });
      link.selected = currentAnchor == link.href || childSelected;

      link.children?.forEach((child) => {
        child.expanded = link.selected;
      });

      link.expanded = true;
    });
    this.navigation.set([...this.navigation()]); // trick to trigger change detection, improve later
  };

  private scrollToUrlLocation = () => {
    setTimeout(() => {
      const element = document.getElementById(
        window.location.hash.substring(1)
      );
      element?.scrollIntoView();

      this.updateNavigationSelection();
    }, 10);
  };
}
