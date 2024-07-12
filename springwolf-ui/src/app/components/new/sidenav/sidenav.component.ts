/* SPDX-License-Identifier: Apache-2.0 */
import { AsyncApiService } from "../../../service/asyncapi/asyncapi.service";
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
import { AsyncApiMapperService } from "../../../service/asyncapi/asyncapi-mapper.service";
import { Location } from "@angular/common";

interface NavigationEntry {
  name: string;
  href: string | undefined;
  selected: boolean;
  tags?: string[];
  children?: NavigationEntry[];
}

function transformMap<K, V, U>(
  source: Map<K, V>,
  func: (key: K, value: V) => U
): Map<K, U> {
  return new Map(Array.from(source, (v) => [v[0], func(v[0], v[1])]));
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
        name: "Info",
        href: AsyncApiMapperService.BASE_URL + "info",
        selected: true,
      });

      const servers: NavigationEntry[] = Array.from(
        asyncapi.servers.keys()
      ).map((key) => ({
        name: key,
        href:
          AsyncApiMapperService.BASE_URL +
          asyncapi.servers.get(key)!.anchorIdentifier,
        selected: false,
      }));
      newNavigation.push({
        name: "Servers",
        href: AsyncApiMapperService.BASE_URL + "servers",
        selected: false,
        children: servers,
      });

      const channels = {
        name: "Channels & Operations",
        href: AsyncApiMapperService.BASE_URL + "channels",
        selected: false,
        children: [] as NavigationEntry[],
      };
      asyncapi.channels.forEach((value) => {
        const channel = {
          name: value.name,
          href: AsyncApiMapperService.BASE_URL + value.anchorIdentifier,
          selected: false,
          tags: [],
          children: value.operations.map((operation) => {
            return {
              name: operation.operation.message.title,
              href: AsyncApiMapperService.BASE_URL + operation.anchorIdentifier,
              tags: [operation.operation.operationType],
              selected: false,
            };
          }),
        };
        channels.children.push(channel);
      });
      newNavigation.push(channels);

      const schemas = {
        name: "Schemas",
        href: AsyncApiMapperService.BASE_URL + "schemas",
        selected: false,
        children: [] as NavigationEntry[],
      };
      asyncapi.components.schemas.forEach((value) => {
        schemas.children.push({
          name: value.title,
          href: AsyncApiMapperService.BASE_URL + "" + value.anchorIdentifier,
          selected: false,
        });
      });
      newNavigation.push(schemas);

      this.navigation = newNavigation;

      this.scrollToUrlLocation();
    });
  }

  ngAfterViewInit() {
    this.scrollableElement.nativeElement.addEventListener(
      "scroll",
      this.updateNavigationSelection
    );
  }

  private updateNavigationSelection = (event: Event) => {
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
          subChildSelected = subChildSelected || subChild.selected;
        });
        child.selected = currentAnchor == child.href || subChildSelected;
        childSelected = childSelected || child.selected;
      });
      link.selected = currentAnchor == link.href || childSelected;
    });
  };

  private scrollToUrlLocation = () => {
    setTimeout(() => {
      const element = document.getElementById(
        window.location.hash.substring(1)
      );
      element?.scrollIntoView();
      console.log(element);
    }, 10);
  };
}
