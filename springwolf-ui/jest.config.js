/** @type {import('jest').Config} */

const esModules = ['@angular', 'jsonpath-plus', '@stoplight', 'nimma'];

const config = {
  preset: 'jest-preset-angular',
  setupFilesAfterEnv: ['<rootDir>/setup-jest.ts'],
  "modulePathIgnorePatterns": [
    "<rootDir>/build/"
  ],
  "transformIgnorePatterns": [
    `<rootDir>/node_modules/(?!.*\\.mjs$|${esModules.join('|')})`,
  ],
};

module.exports = config;