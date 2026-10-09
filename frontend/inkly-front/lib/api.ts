const API_URL = process.env.NEXT_PUBLIC_API_URL ?? "http://localhost:8080";

export type Category = "FICCION" | "CONSPIRACION" | "TERROR" | "MISTERIO" | "OTROS";

export interface ArticleSummary {
  id: number;
  title: string;
  slug: string;
  excerpt: string;
  imageUrl: string | null;
  category: Category;
  status: string;
  views: number;
  publishedAt: string;
}

export interface ArticleFull extends Omit<ArticleSummary, "excerpt"> {
  content: string;
}

interface Page<T> {
  content: T[];
  totalPages: number;
  totalElements: number;
  number: number;
}

export async function getArticles(params: {
  q?: string;
  category?: Category;
  page?: number;
} = {}): Promise<Page<ArticleSummary>> {
  const search = new URLSearchParams();
  if (params.q) search.set("q", params.q);
  if (params.category) search.set("category", params.category);
  search.set("page", String(params.page ?? 0));

  const res = await fetch(`${API_URL}/api/articles?${search.toString()}`, {
    next: { revalidate: 60 },
  });

  if (!res.ok) throw new Error("No se pudieron cargar las historias");
  return res.json();
}

export async function getArticleBySlug(slug: string): Promise<ArticleFull | null> {
  const res = await fetch(`${API_URL}/api/articles/${slug}`, {
    cache: "no-store",
  });

  if (res.status === 404) return null;
  if (!res.ok) throw new Error("No se pudo cargar la historia");
  return res.json();
}

export async function getCategories(): Promise<Category[]> {
  const res = await fetch(`${API_URL}/api/articles/categories`, {
    next: { revalidate: 3600 },
  });
  if (!res.ok) return [];
  return res.json();
}