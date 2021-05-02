export interface OkResponse<T> {
  ok: boolean;
  data: T;
  errors: Array<string>;
}
