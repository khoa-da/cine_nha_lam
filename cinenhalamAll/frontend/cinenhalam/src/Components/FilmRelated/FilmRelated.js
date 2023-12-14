import React, { useEffect, useState } from 'react';
import axios from 'axios';
import FilmPoster from '../../Components/FilmPoster/FilmPoster';
import './FilmRelated.scss'; // Import your main CSS file
import { Box, Typography, Card, CardMedia, CardContent, Grid } from '@mui/material';
import ReactPaginate from 'react-paginate';

function FilmRelated() {
  const [films, setFilms] = useState([]);
  const [loading, setLoading] = useState(true);
  const [pageNumber, setPageNumber] = useState(0);
  const filmsPerPage = 5;

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`http://localhost:8086/api/customer/film/category/Kinh_Dị`);
        setFilms(response.data.listResult);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching related films:', error);
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  const pageCount = Math.ceil(films.length / filmsPerPage);

  const handlePageChange = ({ selected }) => {
    setPageNumber(selected);
  };

  const displayFilms = films
    .slice(pageNumber * filmsPerPage, (pageNumber + 1) * filmsPerPage)
    .map((film) => (
      <Grid item key={film.id} xs={6} sm={5} md={3} lg={2} xl={2}>
        <Card className="FilmRelatedCard">
          <CardMedia component="img" height="140" image={film.img} alt={film.name} className="FilmRelatedImage" />
          <CardContent>
            <Typography gutterBottom variant="h6" component="div" className="FilmRelatedTitle">
              {film.name}
            </Typography>
            {/* Other film details */}
          </CardContent>
        </Card>
      </Grid>
    ));

  if (loading) {
    return <Box>Loading...</Box>;
  }

  return (
    <Box className="centered-container" sx={{ overflowX: 'auto', padding: 2 }}>
      <Typography className="title" variant="h5" gutterBottom>
        Phim Hay Trong Tuần
      </Typography>
      <Grid container spacing={1}>
        {displayFilms}
      </Grid>
      {pageCount > 1 && (
        <ReactPaginate
        previousLabel={'Previous'}
        nextLabel={'Next'}
        breakLabel={'...'}
        breakClassName={'break-me'}
        pageCount={pageCount}
        marginPagesDisplayed={2}
        pageRangeDisplayed={5}
        onPageChange={handlePageChange}
        containerClassName={'pagination'}
        activeClassName={'active'}
        previousClassName={'previous'}
        nextClassName={'next'}
      />
      )}
    </Box>
  );
}

export default FilmRelated;
