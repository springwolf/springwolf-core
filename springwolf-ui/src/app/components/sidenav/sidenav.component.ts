/* SPDX-License-Identifier: Apache-2.0 */
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import {
  AfterViewInit,
  Component,
  ContentChildren,
  ElementRef,
  OnInit,
  QueryList,
  ViewChild,
} from "@angular/core";
import { NavigationTargetDirective } from "./navigation.directive";
import { AsyncApiMapperService } from "../../service/asyncapi/asyncapi-mapper.service";
import { Location } from "@angular/common";

interface NavigationEntry {
  name: string[];
  icon?: string;
  href: string | undefined;
  selected?: boolean;
  collapsed?: boolean;
  tags?: string[];
  children?: NavigationEntry[];
}

@Component({
  selector: "app-sidenav",
  templateUrl: "./sidenav.component.html",
  styleUrls: ["./sidenav.component.css"],
})
export class SidenavComponent implements OnInit, AfterViewInit {
  @ViewChild("scrollableElement") scrollableElement!: ElementRef;
  @ContentChildren(NavigationTargetDirective, { descendants: true })
  navigationTargets!: QueryList<NavigationTargetDirective>;

  navigation: NavigationEntry[] = [];

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
        tags: [asyncapi.servers.get(key)!.protocol],
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
            tags: [operation.operation.operationType],
          };
        });
        const tags = children
          .flatMap((child) => child.tags)
          .flatMap((tags) => tags);

        const channel = {
          name: this.splitForWordBreaking(value.name),
          href: AsyncApiMapperService.BASE_URL + value.anchorIdentifier,
          tags: Array.from(new Set(tags)).sort(),
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

      this.navigation = newNavigation;

      this.scrollToUrlLocation();
    });
  }

  private splitForWordBreaking = (text: string) => {
    // Split by set of characters, but keep separators
    return text.split(/(?<=[.,_/\-])/);
  };

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

    this.navigation.forEach((link) => {
      let childSelected = false;
      link.children?.forEach((child) => {
        let subChildSelected = false;
        child.children?.forEach((subChild) => {
          subChild.selected = currentAnchor == subChild.href;
          subChild.collapsed = !subChild.selected;

          subChildSelected = subChildSelected || subChild.selected;
        });
        child.selected = currentAnchor == child.href || subChildSelected;
        childSelected = childSelected || child.selected;

        child.children?.forEach((subChild) => {
          subChild.collapsed = !child.selected;
        });
      });
      link.selected = currentAnchor == link.href || childSelected;

      link.children?.forEach((child) => {
        child.collapsed = !link.selected;
      });

      link.collapsed = false;
    });
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
