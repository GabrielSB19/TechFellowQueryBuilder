import React from "react";

import { Slider } from "@nextui-org/react";

const SelectYear = () => {
  return (
    <div>
      <div className="text-center mt-3 font-bold text-lg">
        Choose the year range
      </div>
      <div>
        <Slider
          label="Age Range"
          step={1}
          minValue={1997}
          maxValue={2005}
          defaultValue={[2000, 2003]}
          className="max-w-md"
        />
      </div>
    </div>
  );
};

export default SelectYear;
