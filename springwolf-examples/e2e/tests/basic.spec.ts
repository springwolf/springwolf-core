/* SPDX-License-Identifier: Apache-2.0 */
import { test, expect } from "@playwright/test";
import { locateChannelItems, pageIsReady } from "../util/page_object";
import { getExampleAsyncApi } from "../util/example";

test.beforeEach(async ({ page }) => {
  await page.goto("");
});

test("has title", async ({ page }) => {
  await expect(page).toHaveTitle(/Springwolf/);
});

test("can click download and get original asyncapi.json in new tab", async ({
  page,
}) => {
  await pageIsReady(page);

  const newPagePromise = page.waitForEvent("popup");

  await page.click("text=AsyncAPI JSON");
  await page.waitForTimeout(500);

  const newPage = await newPagePromise;
  const content = await newPage.textContent("body pre");
  const asyncApiJson = JSON.parse(content!!);

  expect(asyncApiJson.info.title).toContain("Springwolf example project");
  expect(asyncApiJson).toStrictEqual(getExampleAsyncApi());
});

test("has channels and channel item", async ({ page }) => {
  await expect(locateChannelItems(page)).not.toHaveCount(0);
});
