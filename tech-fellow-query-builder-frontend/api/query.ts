import QueryFormType from "@/types/QueryFormType";
import axios from "axios";

const URL_API = "http://localhost:8000/api/query";

const saveQuery = (query: QueryFormType) => {
  return axios.post(`${URL_API}/create`, query);
};

const getMyQuery = (username: string) => {
  return axios.get(`${URL_API}/getByUser/${username}`);
};

const getQueries = () => {
  return axios.get(`${URL_API}/get`);
};

const query = {
  saveQuery,
  getMyQuery,
  getQueries,
};

export default query;
