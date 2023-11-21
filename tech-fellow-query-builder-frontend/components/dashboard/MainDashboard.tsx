"use client";

import CountryType from "@/types/CountryType";
import RegionWorldType from "@/types/RegionWorldType";
import React, { useEffect, useState } from "react";
import SelectWorld from "./SelectWorld";
import SelectAge from "./SelectAge";
import SelectYear from "./SelectYear";
import SelectGender from "./SelectGender";
import { Button } from "@nextui-org/button";
import GetDataType from "@/types/GetDataType";
import getDataService from "@/service/getDataService";
import { toast } from "react-hot-toast";
import DataGraphType from "@/types/DataGraphType";
import Graph from "../graph/Graph";
import { CircularProgress } from "@nextui-org/react";
import ModalSaveQuery from "../saveQuery/ModalSaveQuery";
import { ReadonlyURLSearchParams } from "next/navigation";

interface MainDashboardProps {
  countries: CountryType[];
  regionWorld: RegionWorldType[];
  params: ReadonlyURLSearchParams;
}

const queryDataForm: GetDataType = {
  codeCountry: "",
  codeRegion: "",
  gender: "",
  ageMin: "",
  ageMax: "",
  yearMin: "",
  yearMax: "",
};

const MainDashboard = ({
  countries,
  regionWorld,
  params,
}: MainDashboardProps) => {
  const [isEnable, setIsEnable] = useState<boolean>(false);
  const [queryData, setQueryData] = useState<GetDataType>(queryDataForm);
  const [isQueryLoad, setIsQuery] = useState<boolean>(false);
  const [dataGraph, setDataGraph] = useState<DataGraphType>();

  useEffect(() => {
    const areAllFieldsComplete =
      (!!queryData.codeCountry || !!queryData.codeRegion) &&
      !!queryData.gender &&
      !!queryData.ageMin &&
      !!queryData.ageMax &&
      !!queryData.yearMin &&
      !!queryData.yearMax;
    setIsEnable(areAllFieldsComplete);
  }, [queryData]);

  const handleProperty = (property: string, value: string) => {
    setQueryData((prevQueryData) => {
      let updatedQueryData = { ...prevQueryData, [property]: value };

      if (property === "codeCountry") {
        updatedQueryData = { ...updatedQueryData, ["codeRegion"]: "" };
      }

      if (property === "codeRegion") {
        updatedQueryData = { ...updatedQueryData, ["codeCountry"]: "" };
      }

      return updatedQueryData;
    });
  };

  const doQuery = async () => {
    try {
      setIsQuery(true);
      const dataGraph = await getDataService.doQuery(queryData);
      setDataGraph(dataGraph);
      setIsQuery(false);
    } catch (error) {
      toast.error("Error in the query");
    }
  };

  return (
    <>
      <div>
        <div className="text-center text-xl mt-4">
          Below you can choose the filters you want to see the behavior of the
          population in the world.
        </div>
        <div className="flex flex-row justify-between">
          <div className="flex flex-col w-[30%]">
            <SelectWorld
              countries={countries}
              regionWorld={regionWorld}
              handleProperty={handleProperty}
              paramsCountry={params.get("codeCountry") || ""}
              paramsRegion={params.get("codeRegion") || ""}
            />
          </div>
          <div className="flex flex-col w-[30%]">
            <SelectAge
              handleProperty={handleProperty}
              paramsAgeMin={params.get("ageMin") || ""}
              paramsAgeMax={params.get("ageMax") || ""}
            />
            <SelectYear
              handleProperty={handleProperty}
              paramsYearMin={params.get("yearMin") || ""}
              paramsYearMax={params.get("yearMax") || ""}
            />
          </div>
          <div className="flex flex-col w-[30%]">
            <SelectGender
              handleProperty={handleProperty}
              paramsGender={params.get("gender") || ""}
            />
          </div>
        </div>
        <div className="mt-10 flex flex-row justify-center w-[100%]">
          <Button
            color="primary"
            className="w-[30%]"
            isDisabled={!isEnable}
            onClick={doQuery}
          >
            Run Query
          </Button>
        </div>
        {isQueryLoad ? (
          <div className="flex items-center justify-center h-[100%] mt-20">
            <CircularProgress label="Loading" size="lg" />
          </div>
        ) : (
          dataGraph && (
            <div>
              <Graph dataGraph={dataGraph} queryData={queryData} />
              <div className="flex flex-row justify-center mb-10 w-[100%]">
                <ModalSaveQuery dataGraph={dataGraph} queryData={queryData} />
              </div>
            </div>
          )
        )}
      </div>
    </>
  );
};

export default MainDashboard;
