<template>
  <div id="root">
    <h1>{{ msg }}</h1>
    <form v-on:submit="onSubmitForm">
      <input type="text" placeholder="회차번호 입력" v-model="round">
      <button>검색</button>
    </form>
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
    <div>
      <h3>로또 번호 랜덤 생성</h3>
      <button v-on:click="generateRandom">랜덤 생성</button>
      <p v-if="randomResult && randomResult.sortedNumbers">랜덤 생성 결과: {{ randomResult.sortedNumbers }}</p>
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
      URL: process.env.VUE_APP_BACK_URL
    }
  },
  methods: {
    async onSubmitForm(e) {
      e.preventDefault();
      console.log(process.env);
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
    generateRandom() {
      this.axios.get(`${this.URL}/lotto/random`)
      .then(response => {
        this.randomResult = response.data;
      })
      .catch(error => {
        console.log(error);
        this.randomResult = "랜덤생성 에러";
      });
    }
  },
  props: {
    msg: String
  }
}
</script>

<style scoped>

</style>