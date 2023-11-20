import GetDataType from "@/types/GetDataType";
import axios from "axios";

const URL_API = "http://localhost:8000/api/doQuery";

const doQuery = (queryData: GetDataType) => {
  return axios.post(`${URL_API}/`, queryData);
};

const getData = {
  doQuery,
};

export default getData;
