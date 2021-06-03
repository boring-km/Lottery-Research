<template>
  <div id="root">
    <h1>{{ msg }}</h1>
    <div>
      <h3>과거 로또 번호 조회</h3>
      <input type="text" placeholder="회차번호 입력" v-model="round">
      <button v-on:click="searchRecord">검색</button>
      <button v-on:click="recentRoundSearch">최근 회차 검색</button>
    </div>
    <p/>
    <div>
      <h3>{{ roundTitle }}</h3>
      <div v-if="roundResult && roundResult.returnValue === 'success'">
        <p>발표일: {{ roundResult.drwNoDate }}</p>
        <p>총 상금: {{ roundResult.totSellamnt }} 원</p>
        <p>1등 상금액: {{ roundResult.firstWinamnt }} 원</p>
        <p>1등 당첨인원 수: {{ roundResult.firstPrzwnerCo }} 명</p>
        <p>로또 번호: {{ roundResult.drwtNo1 }} {{ roundResult.drwtNo2 }} {{ roundResult.drwtNo3 }}
          {{ roundResult.drwtNo4 }} {{ roundResult.drwtNo5 }} {{ roundResult.drwtNo6 }}</p>
        <p>보너스 번호: {{ roundResult.bnusNo }}</p>
      </div>
      <div v-else>
        <p>{{ roundResult }}</p>
      </div>
    </div>
    <p/>
    <hr width="30%">
    <div>
      <h3>로또 번호 랜덤 생성</h3>
      {{ getAverageSum() }}
      <button v-on:click="generateRandom">랜덤 생성</button>
      <div v-if="randomResult && randomResult.sortedNumbers">
        <p>랜덤 생성 결과: {{ randomResult.sortedNumbers }}</p>
        <p>생성된 번호 합: {{ randomResult.sumOfNumbers }}</p>
        <hr width="10%">
        <p>과거 기록에서 번호들의 합 (보너스 번호 제외)</p>
        <p>최소 &lt; 평균  &lt; 최대</p>
        <p>{{ lottoSum.minimum }} &lt; {{ lottoSum.average }} &lt; {{ lottoSum.maximum }}</p>
      </div>
      <p v-else>{{ randomResult }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "MainPage",
  data() {
    return {
      round: '',
      roundTitle: '',
      roundResult: null,
      randomResult: null,
      lottoSum: '',
      URL: process.env.VUE_APP_BACK_URL
    }
  },
  methods: {
    async searchRecord(e) {
      e.preventDefault();
      console.log(process.env, this.round);
      let tempResult = null;
      this.roundTitle = this.round + "회차";
      this.roundResult = "조회 중입니다.";

      await this.axios.get(`${this.URL}/lotto/search/${this.round}`)
          .then(response => {
            tempResult = response.data
          })
          .catch(err => console.log(err));
      if (tempResult && tempResult.returnValue === "success") {
        tempResult.totSellamnt = Number(tempResult.totSellamnt).toLocaleString('en').split(".")[0];
        tempResult.firstWinamnt = Number(tempResult.firstWinamnt).toLocaleString('en').split(".")[0];
        this.roundResult = tempResult;
      } else if (tempResult && tempResult.returnValue === "fail") {
        this.roundResult = "없는 회차번호입니다.";
      } else {
        this.roundResult = "통신 에러입니다.";
      }
    },
    async recentRoundSearch(e) {
      e.preventDefault();
      await this.axios.get(`${this.URL}/lotto/recent`)
      .then(async response => {
        console.log(response);
        this.round = response.data;
        await this.searchRecord(e);
      })
      .catch(err => console.log(err));
    },
    generateRandom() {
      this.axios.get(`${this.URL}/lotto/random`)
      .then(response => {
        this.randomResult = response.data;
      })
      .catch(error => {
        console.log(error);
        this.randomResult = "랜덤생성 에러";
      });
    },
    getAverageSum() {
      this.axios.get(`${this.URL}/lotto/average`)
      .then(response => {
        this.lottoSum = response.data;
      })
    }
  },
  props: {
    msg: String
  }
}
</script>

<style scoped>

</style>