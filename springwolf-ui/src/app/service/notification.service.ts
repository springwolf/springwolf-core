/* SPDX-License-Identifier: Apache-2.0 */
import { Injectable } from "@angular/core";
import { MatSnackBar } from "@angular/material/snack-bar";

@Injectable()
export class NotificationService {
  constructor(private snackBar: MatSnackBar) {}

  public showError(message: string) {
    this.snackBar.open(message, "Close", { verticalPosition: "top" });
  }

  public showWarning(message: string) {
    this.snackBar.open(message, "Close", { duration: 3000 });
  }
}
