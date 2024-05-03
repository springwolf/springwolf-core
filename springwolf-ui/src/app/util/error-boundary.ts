/* SPDX-License-Identifier: Apache-2.0 */
class ApplicationError extends Error {}

export function wrapException<T>(message: string, f: () => T): T {
  try {
    return f();
  } catch (e) {
    if (e instanceof Error) {
      throw new ApplicationError(message + " (" + e.message + ")");
    }
    throw new ApplicationError(message + " (" + e + ")");
  }
}

export function catchException<T>(
  f: () => T,
  errorHandler: (e: any) => void = () => {}
): T | undefined {
  try {
    return f();
  } catch (e) {
    if (errorHandler !== undefined) {
      errorHandler(e);
    }

    return undefined;
  }
}
