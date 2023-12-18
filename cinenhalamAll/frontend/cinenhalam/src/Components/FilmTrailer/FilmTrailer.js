// FilmTrailer.js
import React from 'react';
import YouTube from 'react-youtube';
import './FilmTrailer.scss';

function FilmTrailer({ videoId }) {
  return (
    <div className="trailer-video">
      <div className="youtube-container">
        <YouTube
          videoId={videoId}
          onReady={(event) => {
            event.target.pauseVideo(); // Optionally pause the video when ready
            console.log('Video ready');
          }}
        />
        <div className="trailer-text">Trailer</div>
      </div>
    </div>
  );
}

export default FilmTrailer;
