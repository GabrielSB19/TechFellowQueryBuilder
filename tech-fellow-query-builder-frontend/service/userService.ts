import { toast } from "react-hot-toast";
import user from "@/api/user";
import UserType from "@/types/UserType";

const fetchCreateData = async (username: string) => {
  try {
    const response = await user.createUser(username);
    const userCreated: UserType = response.data;
    return userCreated;
  } catch (error) {
    toast.error("Error al crear el usuario");
  }
};

const userService = {
  fetchCreateData,
};

export default userService;
