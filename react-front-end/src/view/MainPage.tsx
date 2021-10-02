function MainPage() {

  let msg = "";
  let roundTitle = "";

  return (
    <div id="root">
      <h1>{ msg }</h1>
      <div>
        <h3>과거 로또 번호 조회</h3>
        {/*<input type="text" placeholder="회차번호 입력" v-model="round">*/}
        {/*  <button v-on:click="searchRecord">검색</button>*/}
        {/*  <button v-on:click="recentRoundSearch">최근 회차 검색</button>*/}
      </div>
      <p/>
      <div>
        <h3>{ roundTitle }</h3>
        {/*<div v-if="roundResult && roundResult.returnValue === 'success'">*/}
        {/*  <p>발표일: {{ roundResult.drwNoDate }}</p>*/}
        {/*  <p>총 상금: {{ roundResult.totSellamnt }} 원</p>*/}
        {/*  <p>1등 상금액: {{ roundResult.firstWinamnt }} 원</p>*/}
        {/*  <p>1등 당첨인원 수: {{ roundResult.firstPrzwnerCo }} 명</p>*/}
        {/*  <p>로또 번호: {{ roundResult.drwtNo1 }} {{ roundResult.drwtNo2 }} {{ roundResult.drwtNo3 }}*/}
        {/*    {{ roundResult.drwtNo4 }} {{ roundResult.drwtNo5 }} {{ roundResult.drwtNo6 }}</p>*/}
        {/*  <p>보너스 번호: {{ roundResult.bnusNo }}</p>*/}
        {/*</div>*/}
        {/*<div v-else>*/}
        {/*  <p>{{ roundResult }}</p>*/}
        {/*</div>*/}
      </div>
      <p/>
      <div>
        <h3>로또 번호 랜덤 생성</h3>
        { getAverageSum() }
        {/*<button v-on:click="generateRandom">랜덤 생성</button>*/}
        {/*<div v-if="randomResult && randomResult.sortedNumbers">*/}
        {/*  <p>랜덤 생성 결과: {{ randomResult.sortedNumbers }}</p>*/}
        {/*  <p>생성된 번호 합: {{ randomResult.sumOfNumbers }}</p>*/}
        {/*  <hr width="10%">*/}
        {/*    <p>과거 기록에서 번호들의 합 (보너스 번호 제외)</p>*/}
        {/*    <p>최소 &lt; 평균  &lt; 최대</p>*/}
        {/*    <p>{{ lottoSum.minimum }} &lt; {{ lottoSum.average }} &lt; {{ lottoSum.maximum }}</p>*/}
        {/*</div>*/}
        {/*<p v-else>{{ randomResult }}</p>*/}
      </div>
    </div>
  );
}

function getAverageSum() {
  return "";
}

export default MainPage;