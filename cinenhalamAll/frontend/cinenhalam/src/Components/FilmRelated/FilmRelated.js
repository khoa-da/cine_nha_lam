import React, { useEffect, useState } from 'react';
import FilmAPI from '../../Api/FilmAPI';
import { Box, Typography, Card, CardMedia, CardContent, Grid } from '@mui/material';
import ReactPaginate from 'react-paginate';
import './FilmRelated.scss'; // Import your main CSS file

function FilmRelated() {
  const [films, setFilms] = useState([]);
  const [loading, setLoading] = useState(true);
  const [pageNumber, setPageNumber] = useState(0);
  const filmsPerPage = 5;

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await FilmAPI.getAllShowing(); // or FilmAPI.getAllComing();
        setFilms(response.listResult);
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

  // Automatically change page
  useEffect(() => {
    const intervalId = setInterval(() => {
      setPageNumber((prevPageNumber) => (prevPageNumber + 1) % pageCount);
    }, 3000);

    return () => clearInterval(intervalId);
  }, [pageNumber, pageCount]);

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
            {/* Add other film details here */}
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
        Phim Đang Khởi Chiếu
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
