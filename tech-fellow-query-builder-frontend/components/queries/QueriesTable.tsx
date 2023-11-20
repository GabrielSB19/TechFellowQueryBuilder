import React from "react";

import {
  Table,
  TableHeader,
  TableColumn,
  TableBody,
  TableRow,
  TableCell,
  Tooltip,
  Button,
} from "@nextui-org/react";
import QueryType from "@/types/QueryType";
import QueryDetail from "./QueryDetail";

interface QueriesTableProps {
  queries: QueryType[];
}

const QueriesTable = ({ queries }: QueriesTableProps) => {
  return (
    <Table aria-label="Example static collection table">
      <TableHeader>
        <TableColumn>Query Name</TableColumn>
        <TableColumn>User</TableColumn>
        <TableColumn>World</TableColumn>
        <TableColumn>Description</TableColumn>
        <TableColumn>Action</TableColumn>
      </TableHeader>
      <TableBody>
        {queries.map((query, index) => (
          <TableRow key={index}>
            <TableCell>{query.queryName}</TableCell>
            <TableCell>{query.userClient}</TableCell>
            <TableCell>{query.worldType}</TableCell>
            <TableCell>{query.description}</TableCell>
            <TableCell>
              <QueryDetail query={query} />
            </TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>
  );
};

export default QueriesTable;
