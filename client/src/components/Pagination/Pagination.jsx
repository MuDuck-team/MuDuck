import Pagination from 'react-js-pagination';
import './Pagination.css';

function Paging({
  activePage,
  itemsCount,
  totalItemCount,
  pageRange,
  setPage,
}) {
  const handlePageChange = page => {
    setPage(page);
  };

  return (
    <Pagination
      activePage={activePage}
      itemsCountPerPage={itemsCount}
      totalItemsCount={totalItemCount}
      pageRangeDisplayed={pageRange}
      prevPageText="<"
      nextPageText=">"
      onChange={handlePageChange}
    />
  );
}

export default Paging;
