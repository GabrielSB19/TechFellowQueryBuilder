import GetDataType from "@/types/GetDataType";
import QueryToSelect from "@/types/QueryToSelect";
import axios from "axios";

const URL_API = "http://localhost:8000/api/doQuery";

const doQuery = (queryData: GetDataType) => {
  return axios.post(`${URL_API}/`, queryData);
};

const doQueryToSelect = (queryData: QueryToSelect) => {
  return axios.post(`${URL_API}/select`, queryData);
};

const getData = {
  doQuery,
  doQueryToSelect,
};

export default getData;
