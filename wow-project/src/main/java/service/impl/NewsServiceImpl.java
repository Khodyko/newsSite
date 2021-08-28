package service.impl;

import java.util.ArrayList;
import java.util.List;

import bean.News;
import dao.DAOException;
import dao.DaoProvider;
import dao.NewsDao;
import dao.impl.NewsDaoImpl;
import service.NewsService;
import service.ServiceException;

public class NewsServiceImpl implements NewsService {
	private static final DaoProvider DAO_PROVIDER = DaoProvider.getInstance();
	private static final NewsDao NEWS_DAO_IMPL = DAO_PROVIDER.getNewDao();

	@Override
	public void create(News news) throws ServiceException {
		try {
			NEWS_DAO_IMPL.create(news);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void update(News news) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<News> getNewsList(Integer countOf5NewsPage) throws ServiceException {
		List<News> newsList = new ArrayList<News>();
		try {
			newsList = NEWS_DAO_IMPL.getNewsList(countOf5NewsPage);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return newsList;
	}

	@Override
	public Integer getNewsMaxNumber() throws ServiceException {
		Integer newsMaxNumber;
		try {
			newsMaxNumber = NEWS_DAO_IMPL.getNewsMaxNumber();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return newsMaxNumber;
	}

	@Override
	public void deleteNews(Integer id) throws ServiceException {
		try {
			NEWS_DAO_IMPL.delete(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public News getNews(Integer chosenId) throws ServiceException {
		News news = null;
		try {
			news = NEWS_DAO_IMPL.getNews(chosenId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return news;
	}

}
