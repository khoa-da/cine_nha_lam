export const convertDate = (date) => {
  return date.toISOString().slice(0, 10);
};
