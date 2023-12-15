import React, { useEffect, useState } from "react";

function FilmCategories({ filmId }) {
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    // Fetch film categories by film ID
    async function fetchCategories() {
      try {
        const response = await fetch(
          `http://localhost:8086/api/staff/film-category/${1}`
        );
        const data = await response.json();
        setCategories(data);
      } catch (error) {
        console.error('Error fetching film categories:', error);
      }
    }

    fetchCategories();

  }, [filmId]);

  return (
    <div>
      <b>Thể loại:</b> {categories ? categories.join(', ') : 'N/A'}
    </div>
  );
}

export default FilmCategories;
