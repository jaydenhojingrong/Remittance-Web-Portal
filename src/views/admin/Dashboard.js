import React from "react";

// components

import CardTable from "components/Cards/TransactionTable";

export default function Dashboard() {
  return (
    <>
      <div className="flex flex-wrap mt-4">
        <div className="w-full mb-12 px-4">
          <CardTable />
          {localStorage.getItem("username")}
        </div>
      </div>
    </>
  );
}
