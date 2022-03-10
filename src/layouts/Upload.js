import React from "react";
import { Switch, Route, Redirect } from "react-router-dom";

// components

import Navbar from "components/Navbars/AuthNavbar.js";
import FooterSmall from "components/Footers/FooterSmall.js";

// views
import UploadFile from "views/upload/UploadFile";

export default function Upload() {
  return (
    <>
      <Navbar transparent />
      <main>
        <section className="relative w-full h-full py-40 min-h-screen">
          <div
            className="absolute top-0 w-full h-full bg-blueGray-800 bg-no-repeat bg-full"
          ></div>
          <Switch>
            <Route path="/upload/uploadfile" exact component={UploadFile} />
            <Redirect from="/upload" to="/upload/uploadfile" />
          </Switch>
          <FooterSmall absolute />
        </section>
      </main>
    </>
  );
}
