/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnInit } from "@angular/core";
import { UiService } from "./service/ui.service";

export interface Section {
  name: string;
  updated: Date;
}

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
})
export class AppComponent implements OnInit {
  isNewUi: boolean = true;
  json = {
    field: 1,
  };
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
  folders: Section[] = [
    {
      name: "Photos",
      updated: new Date("1/1/16"),
    },
    {
      name: "Recipes",
      updated: new Date("1/17/16"),
    },
    {
      name: "Work",
      updated: new Date("1/28/16"),
    },
  ];
  notes: Section[] = [
    {
      name: "Vacation Itinerary",
      updated: new Date("2/20/16"),
    },
    {
      name: "Kitchen Remodel",
      updated: new Date("1/18/16"),
    },
    {
      name: "Vacation Itinerary",
      updated: new Date("2/20/16"),
    },
    {
      name: "Kitchen Remodel",
      updated: new Date("1/18/16"),
    },
    {
      name: "Vacation Itinerary",
      updated: new Date("2/20/16"),
    },
    {
      name: "Kitchen Remodel",
      updated: new Date("1/18/16"),
    },
    {
      name: "Vacation Itinerary",
      updated: new Date("2/20/16"),
    },
    {
      name: "Kitchen Remodel",
      updated: new Date("1/18/16"),
    },
    {
      name: "Vacation Itinerary",
      updated: new Date("2/20/16"),
    },
    {
      name: "Kitchen Remodel",
      updated: new Date("1/18/16"),
    },
    {
      name: "Vacation Itinerary",
      updated: new Date("2/20/16"),
    },
    {
      name: "Kitchen Remodel",
      updated: new Date("1/18/16"),
    },
    {
      name: "Vacation Itinerary",
      updated: new Date("2/20/16"),
    },
    {
      name: "Kitchen Remodel",
      updated: new Date("1/18/16"),
    },
    {
      name: "Vacation Itinerary",
      updated: new Date("2/20/16"),
    },
    {
      name: "Kitchen Remodel",
      updated: new Date("1/18/16"),
    },
    {
      name: "Vacation Itinerary",
      updated: new Date("2/20/16"),
    },
    {
      name: "Kitchen Remodel",
      updated: new Date("1/18/16"),
    },
    {
      name: "Vacation Itinerary",
      updated: new Date("2/20/16"),
    },
    {
      name: "Kitchen Remodel",
      updated: new Date("1/18/16"),
    },
  ];

  constructor(private uiService: UiService) {}

  ngOnInit() {
    this.uiService.isNewUi$.subscribe((value) => (this.isNewUi = value));
  }
}
