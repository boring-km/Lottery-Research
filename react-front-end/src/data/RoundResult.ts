interface RoundResult {
  message: string;
  totSellamnt: number;
  firstAccumamnt: number;
  firstWinamnt: number;
  firstPrzwnerCo: number;
  returnValue: string;
  drwNoDate: string;
  drwNo: number;
  drwtNo1: number;
  drwtNo2: number;
  drwtNo3: number;
  drwtNo4: number;
  drwtNo5: number;
  drwtNo6: number;
  bnusNo: number;
}

export const getNumbers = (result: RoundResult) => {
  return result.drwtNo1 + ", " + result.drwtNo2 + ", " + result.drwtNo3 + ", " + result.drwtNo4 + ", " +
    result.drwtNo5 + ", " + result.drwtNo6;
}

export default RoundResult;
