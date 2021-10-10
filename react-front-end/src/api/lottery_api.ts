import axios, {AxiosResponse} from "axios";

const URL = process.env.REACT_APP_BACK_URL;

function setResult(response: AxiosResponse, defaultValue: any) {
  if (response.status === 200) {
    return response.data;
  } else {
    return defaultValue;
  }
}

export const getLottoAverageSum = async () => {
  const response = await axios.get(`${URL}/lotto/average`);
  return setResult(response, {});
};

export const searchLottoByRound = async (round: string) => {
  const response = await axios.get(`${URL}/lotto/search/${round}`);
  const result = setResult(response, null);
  if (result && result.returnValue === "success") {
    result.totSellamnt = Number(result.totSellamnt).toLocaleString('en').split(".")[0];
    result.firstWinamnt = Number(result.firstWinamnt).toLocaleString('en').split(".")[0];
    return result;
  } else if (result && result.returnValue === "fail") {
    return "없는 회차번호입니다.";
  } else {
    return "통신 에러입니다.";
  }
};

export const searchRecentRound = async () => {
  const response = await axios.get(`${URL}/lotto/recent`);
  return setResult(response, {});
}

export const getRandomLottoNumber = async () => {
  const response = await axios.get(`${URL}/lotto/random`);
  return setResult(response, {});
}