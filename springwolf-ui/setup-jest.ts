import { provideZonelessChangeDetection } from '@angular/core';
import { getTestBed } from '@angular/core/testing';
import { BrowserTestingModule, platformBrowserTesting } from '@angular/platform-browser/testing';

getTestBed().initTestEnvironment(BrowserTestingModule, platformBrowserTesting(), {});

beforeEach(() => {
  getTestBed().configureTestingModule({
    providers: [provideZonelessChangeDetection()]
  });
});
