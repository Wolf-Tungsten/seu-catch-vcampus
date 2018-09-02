package com.wolfTungsten.vcampus.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.internal.LinkedTreeMap;
import com.wolfTungsten.vcampus.entity.Book;
import com.wolfTungsten.vcampus.utils.Request;
import com.wolfTungsten.vcampus.utils.Response;

public class LibraryController extends BaseController
{
	public LibraryController()
	{
		super();
		this.addHandle("addBook", addBookHandle);
		this.addHandle("updateBook", updateBookHandle);
		this.addHandle("deleteBook", deleteBookHandle);
	}

	private BaseController.BaseHandle addBookHandle = new BaseHandle()
	{

		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String name = (String) request.getParams().get(Book.NAME);
			String isbn = (String) request.getParams().get(Book.ISBN);
			String author = (String) request.getParams().get(Book.AUTHOR);
			long createTime = System.currentTimeMillis() / 1000;
			long updateTime = createTime;
			try
			{
				orm.bookRepository.addBook(name, isbn, author, createTime, updateTime);
				response.setSuccess(true);
				return response;
			} catch (SQLException e)
			{
				e.printStackTrace();
				System.out.println("添加书籍失败");
				response.setSuccess(false);
				response.getBody().put("result", "添加书籍失败");
				return response;

			}

		}
	};
	
	private BaseController.BaseHandle deleteBookHandle = new BaseHandle()
	{
		/**
		 * 根据书本的uuid删除
		 */
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			ArrayList<String> bookUuidList = (ArrayList<String>)request.getParams()
					.get("uuidList");
			for(String uuid:bookUuidList) {
				try
				{
					orm.bookRepository.deleteBookByUuid(uuid);
				} catch (SQLException e)
				{
					response.setSuccess(false);
					e.printStackTrace();
				}
			}
			response.setSuccess(true);
			return response;
		}
	};
	private BaseController.BaseHandle updateBookHandle = new BaseHandle()
	{

		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			ArrayList<LinkedTreeMap<String, Object>> booksMap =
					(ArrayList<LinkedTreeMap<String, Object>>) request.getParams()
					.get("booksList");
			for (int i = 0; i < booksMap.size();i++)
			{
				try
				{
					orm.bookRepository.updateBook(booksMap.get(i));
										
				} catch (SQLException e)
				{
					response.setSuccess(false);
					// response.getBody().put("Cause by: ", e.getMessage());
					e.printStackTrace();
					return response;
				}
				

			}
			response.setSuccess(true);
			return response;
		}
	};

}