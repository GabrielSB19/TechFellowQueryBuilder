"use client";

import NavbarComponent from "@/components/navbar/NavbarComponent";
import QueryType from "@/types/QueryType";
import React, { useState, useEffect } from "react";
import { toast } from "react-hot-toast";
import queryService from "@/service/queryService";
import QueriesTable from "@/components/queries/QueriesTable";

const QueriesPage = () => {
  const [queries, setQueries] = useState<QueryType[]>([]);

  const fetchDataAllQueries = async () => {
    try {
      const response = await queryService.fetchDataAllQueries();
      setQueries(response);
    } catch (error) {
      toast.error("Error fetching queries");
    }
  };

  useEffect(() => {
    fetchDataAllQueries();
  }, []);

  return (
    <div>
      <NavbarComponent selected="queries" />
      {queries.length === 0 ? (
        <div className="text-centr font-bold text-2xl mt-10">
          There are no queries saved
        </div>
      ) : (
        <QueriesTable queries={queries} />
      )}
    </div>
  );
};

export default QueriesPage;
