import DataGraphType from "@/types/DataGraphType";
import React, { useRef, useEffect } from "react";
import Chart from "chart.js/auto";
import GetDataType from "@/types/GetDataType";

interface GraphProps {
  dataGraph: DataGraphType;
  queryData: GetDataType;
}

const Graph = ({ dataGraph, queryData }: GraphProps) => {
  const data = dataGraph;
  let labelY = "Persons amount";
  if (queryData.ageMax === queryData.ageMin) {
    labelY += ` with ${queryData.ageMin} years old`;
  } else {
    labelY += ` between ${queryData.ageMin} and ${queryData.ageMax} years old`;
  }

  const chartRef = useRef(null);

  useEffect(() => {
    if (chartRef.current) {
      const ctx = chartRef.current;
      new Chart(ctx, {
        type: "line",
        data: {
          labels: data.years,
          datasets: [
            {
              label: data.worldType,
              data: data.values,
              borderColor: "rgba(75, 192, 192, 1)",
              borderWidth: 2,
              fill: false,
            },
          ],
        },
        options: {
          plugins: {
            title: {
              display: true,
              text: `Population of ${data.worldType}`,
            },
          },
          scales: {
            x: {
              type: "linear",
              position: "bottom",
              title: {
                display: true,
                text: "Year",
              },
            },
            y: {
              type: "linear",
              position: "left",
              title: {
                display: true,
                text: labelY,
              },
            },
          },
        },
      });
    }
  }, [data]);

  return (
    <div className="mt-5 mb-10">
      <canvas ref={chartRef} width="400" height="200" />
    </div>
  );
};

export default Graph;
