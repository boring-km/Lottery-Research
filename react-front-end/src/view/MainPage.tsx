import {useCallback, useEffect, useState} from "react";
import {getLottoAverageSum, getRandomLottoNumber, searchLottoByRound, searchRecentRound} from "../api/lottery_api";
import RandomResult from "../data/RandomResult";
import RoundResult from "../data/RoundResult";
import LottoSum from "../data/LottoSum";

const MainPage = () => {

  const [roundTitle, setRoundTitle] = useState("");
  const [round, setRound]: [string, any] = useState("");
  const [roundResult, setRoundResult]: [(RoundResult | undefined), any] = useState<RoundResult>();
  const [randomResult, setRandomResult]: [(RandomResult | undefined), any] = useState<RandomResult>();
  const [lottoSum, setLottoSum]: [(LottoSum | any), any] = useState<LottoSum>();

  useEffect(() => {
    getLottoAverageSum().then(result => setLottoSum(result));
  }, []);

  const recentRoundSearch = async () => {
    const round = await searchRecentRound();
    await searchRecord(round);
  }

  const generateRandom = async () => {
    const result = await getRandomLottoNumber();
    setRandomResult(result);
  }

  const searchRecord = async (tempRound: string) => {
    setRound(tempRound);
    const curRound = tempRound != null ? tempRound : round;
    setRoundTitle(curRound + "회차");
    setRoundResult("조회 중입니다.");

    const tempResult: (RoundResult | any) = await searchLottoByRound(curRound);

    if (tempResult && tempResult.returnValue === "success") {
      tempResult.totSellamnt = Number(tempResult.totSellamnt).toLocaleString('en').split(".")[0];
      tempResult.firstWinamnt = Number(tempResult.firstWinamnt).toLocaleString('en').split(".")[0];
      setRoundResult(tempResult);
    } else if (tempResult && tempResult.returnValue === "fail") {
      setRoundResult("없는 회차번호입니다.");
    } else {
      setRoundResult("통신 에러입니다.");
    }
  }

  const onChange = useCallback(e => {
    setRound(e.target.value);
  },[]);

  const RoundResultComponent = () => {
    if (roundResult && roundResult.returnValue === "success") {
      return (
        <div>
          <p>발표일: { roundResult.drwNoDate }</p>
          <p>총 상금: { roundResult.totSellamnt } 원</p>
          <p>1등 상금액: { roundResult.firstWinamnt } 원</p>
          <p>1등 당첨인원 수: { roundResult.firstPrzwnerCo } 명</p>
          <p>로또 번호: { roundResult.drwtNo1 } { roundResult.drwtNo2 } { roundResult.drwtNo3 }
            { roundResult.drwtNo4 } { roundResult.drwtNo5 } { roundResult.drwtNo6 }</p>
          <p>보너스 번호: { roundResult.bnusNo }</p>
        </div>
      );
    }
    return ( <div> <p>{ roundResult }</p> </div> );
  }

  const RandomResultComponent = () => {
    if (randomResult && randomResult.sortedNumbers) {
      const result: RandomResult = randomResult;
      return (
        <div>
          <p>랜덤 생성 결과: { result.sortedNumbers.toLocaleString() }</p>
          <p>생성된 번호 합: { result.sumOfNumbers }</p>
          <p>과거 기록에서 번호들의 합 (보너스 번호 제외)</p>
          <p>최소 &lt; 평균  &lt; 최대</p>
          <p>{ lottoSum.minimum } &lt; { lottoSum.average } &lt; { lottoSum.maximum }</p>
        </div>
      );
    }
    return ( <div> <p> { randomResult } </p> </div> )
  }

  return (
    <div id="root">
      <div>
        <h3>과거 로또 번호 조회</h3>
        <input type="text" placeholder="회차번호 입력" value={ round } onChange={ onChange } contentEditable={ true }/>
        <button onClick={ async () => { await searchRecord(round); } }>검색</button>
        <button onClick={ recentRoundSearch }>최근 회차 검색</button>
      </div>
      <p/>
      <div>
        <h3>{ roundTitle }</h3>
        <RoundResultComponent/>
      </div>
      <p/>
      <div>
        <h3>로또 번호 랜덤 생성</h3>
        <button onClick={ generateRandom }>랜덤 생성</button>
        <RandomResultComponent />
      </div>
    </div>
  );
}

export default MainPage;
