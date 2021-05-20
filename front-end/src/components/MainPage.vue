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
      <div v-if="result && result.returnValue === 'success'">
        <p>발표일: {{ result.drwNoDate }}</p>
        <p>총 상금: {{ result.totSellamnt }} 원</p>
        <p>1등 상금액: {{ result.firstWinamnt }} 원</p>
        <p>1등 당첨인원 수: {{ result.firstPrzwnerCo }} 명</p>
        <p>로또 번호: {{ result.drwtNo1 }} {{ result.drwtNo2 }} {{ result.drwtNo3 }}
          {{ result.drwtNo4 }} {{ result.drwtNo5 }} {{ result.drwtNo6 }}</p>
        <p>보너스 번호: {{ result.bnusNo }}</p>
      </div>
      <div v-else>
        <p>{{ result }}</p>
      </div>
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
      result: null
    }
  },
  methods: {
    async onSubmitForm(e) {
      e.preventDefault();
      let tempResult = null;
      this.roundTitle = this.round + "회차";
      await this.axios.get(`http://localhost:8080/lotto/search/${this.round}`)
          .then(response => {
            tempResult = response.data
          })
          .catch(err => console.log(err));
      if (tempResult && tempResult.returnValue === "success") {
        tempResult.totSellamnt = Number(tempResult.totSellamnt).toLocaleString('en').split(".")[0];
        tempResult.firstWinamnt = Number(tempResult.firstWinamnt).toLocaleString('en').split(".")[0];
        this.result = tempResult;
      } else if (tempResult && tempResult.returnValue === "fail") {
        this.result = "없는 회차번호입니다.";
      } else {
        this.result = "통신 에러입니다.";
      }
    }
  },
  props: {
    msg: String
  }
}
</script>

<style scoped>

</style>