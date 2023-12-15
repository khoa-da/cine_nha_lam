// FilmTrailer.js
import React from 'react';
import YouTube from 'react-youtube';

function FilmTrailer({ videoId }) {
  return (
    <div className="trailer-video">
      <YouTube
        videoId={videoId}
        onReady={() => console.log('Video ready')}
        onPlay={() => console.log('Video played')} 
      />
    </div>
  );
}

export default FilmTrailer;
