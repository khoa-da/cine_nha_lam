import React from 'react';
import YouTube from 'react-youtube';
import './FilmTrailer.scss';

function FilmTrailer({ videoId }) {
  return (
    <div className="trailer-video">
      <div className="youtube-container">
        <div className="trailername">Trailer</div>
        <YouTube
          videoId={videoId}
          onReady={(event) => {
            event.target.pauseVideo();
            console.log('Video ready');
          }}
        />
        <div className="trailer-text"></div>
      </div>
    </div>
  );
}

export default FilmTrailer;
