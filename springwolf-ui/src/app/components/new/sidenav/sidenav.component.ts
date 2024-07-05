/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-sidenav",
  templateUrl: "./sidenav.component.html",
  styleUrls: ["./sidenav.component.css"],
})
export class SidenavComponent {
  links = [
    {
      name: "Info",
      url: "#info",
      isActive: true,
    },
    {
      name: "Servers",
      url: "#servers",
      isActive: false,
    },
    {
      name: "Channels",
      url: "#channels",
      isActive: false,
    },
    {
      name: "Operations",
      url: "#operations",
      isActive: false,
    },
    {
      name: "Schemas",
      url: "#schemas",
      isActive: false,
    },
  ];

  constructor() {}
}
