"use client";

import NavbarComponent from "@/components/navbar/NavbarComponent";
import QueriesTable from "@/components/queries/QueriesTable";
import React, { useEffect, useState } from "react";
import queryService from "@/service/queryService";
import QueryType from "@/types/QueryType";
import { toast } from "react-hot-toast";

const MyQueriesPage = () => {
  const [queries, setQueries] = useState<QueryType[]>([]);

  const fetchQueries = async () => {
    try {
      const username = localStorage.getItem("username") || "";
      const response = await queryService.fetchDataQueries(username);
      setQueries(response);
    } catch (error) {
      toast.error("Error Fetching Queries");
    }
  };

  useEffect(() => {
    fetchQueries();
  }, []);

  return (
    <div>
      <NavbarComponent selected="My Queries" />
      {queries.length === 0 ? (
        <div className="text-centr font-bold text-2xl mt-10">
          You do not have a query saved
        </div>
      ) : (
        <QueriesTable queries={queries} />
      )}
    </div>
  );
};

export default MyQueriesPage;
