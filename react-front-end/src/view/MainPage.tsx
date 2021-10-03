import axios, {AxiosResponse} from "axios";
import {useCallback, useEffect, useState} from "react";
import RandomResult from "../data/RandomResult";

function MainPage() {

  const [roundTitle, setRoundTitle] = useState("");
  const [round, setRound] = useState();
  const [roundResult, setRoundResult]: [any, any] = useState();
  const [randomResult, setRandomResult]: [any, any] = useState();
  const [lottoSum, setLottoSum]: [any, any] = useState();
  
  const URL = process.env.REACT_APP_BACK_URL;

  useEffect(() => {
    const setAverageSum = () => {
      axios.get(`${URL}/lotto/average`)
        .then(response => {
          setLottoSum(response.data);
        });
    }
    setAverageSum();
  }, [URL]);

  const onChange = useCallback(e => {
    setRound(e.target.value);
  },[]);


  const searchRecord = async (tempRound: any) => {
    let curRound = round;
    if (tempRound != null)
      curRound = tempRound;
    console.log(process.env, curRound);
    let tempResult: any;
    setRoundTitle(curRound + "회차");
    setRoundResult("조회 중입니다.");

    await axios.get(`${URL}/lotto/search/${curRound}`)
      .then(response => {
        tempResult = response.data
      })
      .catch(err => console.log(err));
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

  const recentRoundSearch = async () => {
    const result: AxiosResponse = await axios.get(`${URL}/lotto/recent`);
    const round = result.data;
    setRound(round);
    await searchRecord(round);
  }

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

  const generateRandom = async () => {
    await axios.get(`${URL}/lotto/random`)
      .then(response => {
        setRandomResult(response.data);
      })
      .catch(error => {
        console.log(error);
        setRandomResult("랜덤생성 에러");
      });
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