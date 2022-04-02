import React, { useEffect, useState } from "react";
import axios from "axios";

// components

import TransactionTable from "components/Cards/TransactionTable";

export default function Dashboard() {
  const [data, setData] = useState([])
  localStorage.loaded = false;
  return (
    <>
      <div className="flex flex-wrap mt-4">
        <div className="w-full mb-12 px-4">
          <TransactionTable />
        </div>
      </div>
    </>
  );
}
