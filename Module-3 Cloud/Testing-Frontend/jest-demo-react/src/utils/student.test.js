import {isEligible} from "./student.js";
describe('isEligible', () => {
  test('should return true for attendance >= 75', () => {
    expect(isEligible(80)).toBe(true);
  });
});