import GetDataType from "@/types/GetDataType";
import { toast } from "react-hot-toast";
import getData from "@/api/getData";
import DataGraphType from "@/types/DataGraphType";

const doQuery = async (queryData: GetDataType) => {
  try {
    const response = await getData.doQuery(queryData);
    const dataGraph: DataGraphType = response.data;
    console.log(dataGraph);
    return dataGraph;
  } catch (error) {
    toast.error("Error al enviar la consulta");
  }
};

const getDataService = {
  doQuery,
};

export default getDataService;
