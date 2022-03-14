import React from "react";

// components

import MappingTable from "components/Mapping/MappingTable.js";
import MapFields from "components/Mapping/MapFields.js";

export default function Mapping() {
  return (
    <>
      <div className="flex flex-wrap">
        <div className="w-full px-4">
          <div className="relative flex flex-col min-w-0 break-words bg-white w-full mb-6 shadow-lg rounded">
            <MapFields />
            {/* <MappingTable /> */}
          </div>
        </div>
      </div>
    </>
  );
}
