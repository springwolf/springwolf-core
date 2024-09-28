/** @type {import('jest').Config} */

const esModules = ['@angular', 'jsonpath-plus', '@stoplight', 'nimma', 'prism-code-editor'];

const config = {
  preset: 'jest-preset-angular',
  reporters: [
    "default",
    "jest-junit"
  ],
  setupFilesAfterEnv: ['<rootDir>/setup-jest.ts'],
  "modulePathIgnorePatterns": [
    "<rootDir>/build/"
  ],
  "transformIgnorePatterns": [
    `<rootDir>/node_modules/(?!.*\\.mjs$|${esModules.join('|')})`,
  ],
  moduleNameMapper: {
    '\\.(css|scss)$': '<rootDir>/setup-jest.ts', // any ts file
  },
};

module.exports = config;
