/* SPDX-License-Identifier: Apache-2.0 */
import {AsyncApiService} from "../../../service/asyncapi/asyncapi.service";
import {AfterViewInit, Component, ContentChildren, ElementRef, OnInit, QueryList, ViewChild,} from "@angular/core";
import {NavigationTargetDirective} from "./navigation.directive";

interface NavigationEntry {
  name: string;
  href: string | undefined;
  selected: boolean;
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

  constructor(private asyncApiService: AsyncApiService) {}

  ngOnInit(): void {
    this.asyncApiService.getAsyncApi().subscribe((asyncapi) => {
      const newNavigation: NavigationEntry[] = [];

      newNavigation.push({ name: "Info", href: "#info", selected: true });

      newNavigation.push({ name: "Servers", href: "#servers", selected: false, });

      const channels = {
        name: "Channels",
        href: "#channels",
        selected: false,
        children: [] as NavigationEntry[],
      };
      asyncapi.channelOperations.forEach((value) => {
        channels.children.push({
          name: value.name,
          href: "#" + value.anchorIdentifier,
          selected: false,
        });
      });
      newNavigation.push(channels);

      const schemas = {
        name: "Schemas",
        href: "#schemas",
        selected: false,
        children: [] as NavigationEntry[],
      };
      asyncapi.components.schemas.forEach((value) => {
        schemas.children.push({
          name: value.title,
          href: "#" + value.anchorIdentifier,
          selected: false,
        });
      });
      newNavigation.push(schemas);

      this.navigation = newNavigation;
    });
  }

  ngAfterViewInit() {
    this.scrollableElement.nativeElement.addEventListener(
      "scroll",
      (event: Event) => {
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
              currentAnchor = "#" + nativeElement.getAttribute("id");
            }
          });

        this.navigation.forEach((link) => {
          let childSelected = false;
          link.children?.forEach((child) => {
            child.selected = currentAnchor == child.href;
            childSelected = childSelected || child.selected;
          });
          link.selected = currentAnchor == link.href || childSelected;
        });
      }
    );
  }
}
