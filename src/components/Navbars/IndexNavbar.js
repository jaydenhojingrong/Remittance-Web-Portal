/*eslint-disable*/
import React from "react";
import { NavLink } from "react-router-dom";

// components

import PagesDropdown from "components/Dropdowns/PagesDropdown.js";

export default function Navbar(props) {
  const [navbarOpen, setNavbarOpen] = React.useState(false);
  return (
    <>
      <nav className="top-0 absolute z-50 w-full flex flex-wrap items-center justify-between px-2 py-3 navbar-expand-lg">
        <div className="container px-4 mx-auto flex flex-wrap items-center justify-between">
          <div className="w-full relative flex justify-between lg:w-auto lg:static lg:block lg:justify-start">
            <div
              className="text-white text-sm font-bold leading-relaxed inline-block mr-4 py-2 whitespace-nowrap uppercase"
              to="/"
            >
              Name 
            </div>

          </div>
          <div>
            <div className="flex flex-col lg:flex-row list-none lg:ml-auto">
            <button
                  className="bg-white text-blueGray-700 active:bg-blueGray-50 text-xs font-bold uppercase px-4 py-2 rounded shadow hover:shadow-md outline-none focus:outline-none ml-3 mb-3 ease-linear"
                  type="button"
                >
                  <i className="fas fa-arrow-alt-circle-up"></i> <NavLink to="/upload/uploadfile">Upload</NavLink>
                </button>
            </div >
          </div>
        </div>
      </nav>
    </>
  );
}
