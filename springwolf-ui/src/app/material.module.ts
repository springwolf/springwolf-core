/* SPDX-License-Identifier: Apache-2.0 */
import { NgModule } from "@angular/core";

import { MatButtonModule } from "@angular/material/button";
import { MatIconModule } from "@angular/material/icon";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatCardModule } from "@angular/material/card";
import { MatExpansionModule } from "@angular/material/expansion";
import { MatTabsModule } from "@angular/material/tabs";
import { MatDividerModule } from "@angular/material/divider";
import { ClipboardModule } from "@angular/cdk/clipboard";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatSelectModule } from "@angular/material/select";
import { MatChipsModule } from "@angular/material/chips";
import { MatSlideToggleModule } from "@angular/material/slide-toggle";
import { MatListModule } from "@angular/material/list";
import { MatSidenavModule } from "@angular/material/sidenav";

const modules = [
  MatButtonModule,
  MatIconModule,
  MatToolbarModule,
  MatCardModule,
  MatExpansionModule,
  MatTabsModule,
  MatDividerModule,
  MatListModule,
  ClipboardModule,
  MatSnackBarModule,
  MatFormFieldModule,
  MatSelectModule,
  MatChipsModule,
  MatSlideToggleModule,
  MatSidenavModule,
];

@NgModule({
  imports: modules,
  exports: modules,
})
export class MaterialModule {}
