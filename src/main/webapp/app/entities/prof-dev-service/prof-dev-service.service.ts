import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProfDevService } from 'app/shared/model/prof-dev-service.model';

type EntityResponseType = HttpResponse<IProfDevService>;
type EntityArrayResponseType = HttpResponse<IProfDevService[]>;

@Injectable({ providedIn: 'root' })
export class ProfDevServiceService {
  public resourceUrl = SERVER_API_URL + 'api/prof-dev-services';

  constructor(protected http: HttpClient) {}

  create(profDevService: IProfDevService): Observable<EntityResponseType> {
    return this.http.post<IProfDevService>(this.resourceUrl, profDevService, { observe: 'response' });
  }

  update(profDevService: IProfDevService): Observable<EntityResponseType> {
    return this.http.put<IProfDevService>(this.resourceUrl, profDevService, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IProfDevService>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProfDevService[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
