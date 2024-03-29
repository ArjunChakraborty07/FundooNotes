package com.bridgelabz.fundoo.repositoryimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.configuration.ElasticSearchConfig;
import com.bridgelabz.fundoo.entity.NotesEntity;
import com.bridgelabz.fundoo.repository.IElasticSearchRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@Repository
public class ElasticSearchRepositoryImpl implements IElasticSearchRepository {

	@Autowired
	private ElasticSearchConfig config;
	@Autowired
	private ObjectMapper objectmapper;

	private static final String INDEX = "Fundoo";
	private static final String TYPE = "note_details";

	@Override
	public void createNote(NotesEntity note) {
		IndexRequest indexrequest = new IndexRequest(INDEX, TYPE, String.valueOf(note.getNoteId()))
				.source(objectmapper.convertValue(note, Map.class));
		
		try {
			config.client().index(indexrequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateNote(NotesEntity note) {

		UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE, String.valueOf(note.getNoteId()))
				.doc(objectmapper.convertValue(note, Map.class));
		UpdateResponse updateResponse = null;
		try {
			updateResponse = config.client().update(updateRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return updateResponse.getResult().name();
	}

	@Override
	public String deleteNote(NotesEntity note) {

		objectmapper.convertValue(note, Map.class);
		DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, String.valueOf(note.getNoteId()));
		DeleteResponse deleteResponse = null;
		try {
			deleteResponse = config.client().delete(deleteRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return deleteResponse.getResult().name();
	}

	@Override
	public List<NotesEntity> searchByTitle(String title) {

		SearchRequest searchRequest = new SearchRequest(INDEX);
		SearchSourceBuilder searchSource = new SearchSourceBuilder();


		searchSource.query(QueryBuilders.matchQuery("title", title));
		searchRequest.source(searchSource);
		SearchResponse searchResponse = null;
		try {
			searchResponse = config.client().search(searchRequest, RequestOptions.DEFAULT);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return getResult(searchResponse);
	}

	private List<NotesEntity> getResult(SearchResponse searchResponse) {
		SearchHit[] searchHits = searchResponse.getHits().getHits();
		List<NotesEntity> notes = new ArrayList<>();
		if (searchHits.length > 0) {
			Arrays.stream(searchHits)
					.forEach(hit -> notes.add(objectmapper.convertValue(hit.getSourceAsMap(), NotesEntity.class)));
		}
		return notes;
	}

}