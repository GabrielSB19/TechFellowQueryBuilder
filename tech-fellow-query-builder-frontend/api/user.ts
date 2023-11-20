import axios from "axios";

const URL_API = "http://localhost:8000/api/user";

const createUser = (username: string) => {
  return axios.post(`${URL_API}/create`, { username });
};

const user = {
  createUser,
};

export default user;
