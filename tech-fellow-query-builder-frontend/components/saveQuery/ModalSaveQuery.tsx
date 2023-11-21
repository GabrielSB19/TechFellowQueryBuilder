import React, { useEffect } from "react";
import { useState } from "react";
import queryService from "@/service/queryService";
import { Input } from "@nextui-org/react";
import { Textarea } from "@nextui-org/react";
import {
  Modal,
  ModalContent,
  ModalHeader,
  ModalBody,
  ModalFooter,
  Button,
  useDisclosure,
} from "@nextui-org/react";
import DataGraphType from "@/types/DataGraphType";
import QueryFormType from "@/types/QueryFormType";
import { toast } from "react-hot-toast";

interface ModalSaveQueryProps {
  queryData: DataGraphType;
}

const queryFormSave: QueryFormType = {
  queryName: "",
  description: "",
  userClient: "",
  query: "",
  worldType: "",
};

const ModalSaveQuery = ({ queryData }: ModalSaveQueryProps) => {
  console.log(queryData);

  const { isOpen, onOpen, onOpenChange } = useDisclosure();
  const [queryForm, setQueryForm] = useState<QueryFormType>(queryFormSave);

  useEffect(() => {
    const querySQL = queryData.query;
    const username = localStorage.getItem("username") || "";
    const worldType = queryData.worldType;
    setQueryForm((prevQueryForm) => ({
      ...prevQueryForm,
      userClient: username,
      query: querySQL,
      worldType: worldType,
    }));
  }, []);

  const handleQueryForm = (property: string, value: string) => {
    setQueryForm((prevQueryForm) => {
      let updatedQueryForm = { ...prevQueryForm, [property]: value };
      return updatedQueryForm;
    });
  };

  const handleSaveQuery = async () => {
    try {
      await queryService.fetchDataSaveQuery(queryForm);
      toast.success("Query saved successfully");
    } catch (error) {
      toast.error("Error saving query");
    }
  };

  return (
    <>
      <Button onPress={onOpen} color="primary" className="w-[20%]">
        Save Query
      </Button>
      <Modal isOpen={isOpen} onOpenChange={onOpenChange}>
        <ModalContent>
          {(onClose) => (
            <>
              <ModalHeader className="flex flex-col gap-1">
                Save Query
              </ModalHeader>
              <ModalBody>
                <p>Input the data to save your query.</p>
                <Input
                  value={queryForm.queryName}
                  onChange={(value) =>
                    handleQueryForm("queryName", value.target.value)
                  }
                  type="text"
                  label="Name Query"
                  placeholder="Enter the name of the query"
                />
                <Textarea
                  value={queryForm.description}
                  label="Description"
                  placeholder="Enter your description"
                  onChange={(value) =>
                    handleQueryForm("description", value.target.value)
                  }
                />
              </ModalBody>
              <ModalFooter>
                <Button color="danger" variant="light" onPress={onClose}>
                  Close
                </Button>
                <Button
                  color="primary"
                  onPress={onClose}
                  onClick={handleSaveQuery}
                >
                  Guardar
                </Button>
              </ModalFooter>
            </>
          )}
        </ModalContent>
      </Modal>
    </>
  );
};

export default ModalSaveQuery;
