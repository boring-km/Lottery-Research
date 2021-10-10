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
  return setResult(response, {});
};

export const searchRecentRound = async () => {
  const response = await axios.get(`${URL}/lotto/recent`);
  return setResult(response, {});
}

export const getRandomLottoNumber = async () => {
  const response = await axios.get(`${URL}/lotto/random`);
  return setResult(response, {});
}