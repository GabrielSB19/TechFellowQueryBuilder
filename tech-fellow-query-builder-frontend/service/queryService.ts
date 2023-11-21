import query from "@/api/query";
import { toast } from "react-hot-toast";
import QueryFormType from "@/types/QueryFormType";

const fetchDataSaveQuery = async (querySave: QueryFormType) => {
  try {
    console.log(querySave);

    const response = await query.saveQuery(querySave);
    const queryGet = response.data;
    return queryGet;
  } catch (error) {
    toast.error("Error al cargar las consultas");
  }
};

const fetchDataQueries = async (username: string) => {
  try {
    const response = await query.getMyQuery(username);
    const queries = response.data;
    return queries;
  } catch (error) {
    toast.error("Error al cargar las consultas");
  }
};

const fetchDataAllQueries = async () => {
  try {
    const response = await query.getQueries();
    const queries = response.data;
    console.log(queries);
    return queries;
  } catch (error) {
    toast.error("Error al cargar las consultas");
  }
};

const queryService = {
  fetchDataSaveQuery,
  fetchDataQueries,
  fetchDataAllQueries,
};

export default queryService;
