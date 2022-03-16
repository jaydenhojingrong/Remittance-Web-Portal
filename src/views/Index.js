/*eslint-disable*/
import React from "react";
import { Switch, Route, Redirect, Link } from "react-router-dom";

import Navbar from "components/Navbars/IndexNavbar";
import FooterSmall from "components/Footers/FooterSmall.js";

export default function Index() {
  return (
    <>
    <Navbar transparent />
      <main>
        <section className="relative w-full h-full py-40 min-h-screen">
          <div
            className="absolute top-0 w-full h-full bg-blueGray-800 bg-no-repeat bg-full"
          ></div>
          <Switch>
            <Redirect from="/" to="/auth" />
          </Switch>
          <FooterSmall absolute />
        </section>
      </main>
    </>
  );
}
